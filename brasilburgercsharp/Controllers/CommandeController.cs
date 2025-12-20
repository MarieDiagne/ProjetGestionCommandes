using Microsoft.AspNetCore.Mvc;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.ViewModels.Commande;
using System.Security.Claims;
using Microsoft.AspNetCore.Authorization;

namespace brasilburgercsharp.Controllers
{
    [Authorize] // Nécessite d'être connecté pour commander
    public class CommandeController : Controller
    {
        private readonly ICommandeService _commandeService;
        private readonly IPanierService _panierService;
        private readonly IZoneService _zoneService; // Pour lister les zones de livraison

        public CommandeController(
            ICommandeService commandeService, 
            IPanierService panierService,
            IZoneService zoneService)
        {
            _commandeService = commandeService;
            _panierService = panierService;
            _zoneService = zoneService;
        }

        // Affiche le formulaire de finalisation de commande
        [HttpGet]
        public async Task<IActionResult> Passer()
        {
            var panier = _panierService.GetPanier();
            if (panier.Lignes.Count == 0)
            {
                return RedirectToAction("Index", "Catalogue");
            }

            ViewBag.Zones = await _zoneService.GetAllZonesAsync();
            return View(new CommandeViewModel());
        }

        // Traite la validation de la commande
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Confirmer(CommandeViewModel model)
        {
            if (!ModelState.IsValid)
            {
                ViewBag.Zones = await _zoneService.GetAllZonesAsync();
                return View("Passer", model);
            }

            // Récupération de l'ID du client connecté
            var userIdClaim = User.FindFirst(ClaimTypes.NameIdentifier);
            if (userIdClaim == null) return RedirectToAction("Login", "Account");

            int clientId = int.Parse(userIdClaim.Value);

            try
            {
                int commandeId = await _commandeService.CreerCommandeAsync(model, clientId);
                
                // Vider le panier après succès
                _panierService.ViderPanier();

                TempData["Success"] = "Votre commande a été enregistrée avec succès !";
                return RedirectToAction("Details", new { id = commandeId });
            }
            catch (Exception ex)
            {
                ModelState.AddModelError("", "Une erreur est survenue lors de la commande : " + ex.Message);
                ViewBag.Zones = await _zoneService.GetAllZonesAsync();
                return View("Passer", model);
            }
        }

        // Affiche les détails d'une commande spécifique
        public async Task<IActionResult> Details(int id)
        {
            var commande = await _commandeService.GetDetailsAsync(id);
            if (commande == null) return NotFound();

            return View(commande);
        }

        // Liste des commandes du client
        public async Task<IActionResult> MesCommandes()
        {
            var userIdClaim = User.FindFirst(ClaimTypes.NameIdentifier);
            if (userIdClaim == null) return RedirectToAction("Login", "Account");

            int clientId = int.Parse(userIdClaim.Value);
            var commandes = await _commandeService.GetCommandesByClientAsync(clientId);
            
            return View(commandes);
        }
    }
}
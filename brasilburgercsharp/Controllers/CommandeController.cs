using Microsoft.AspNetCore.Mvc;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Models.Entities;
using BrasilBurger.Models.Enums;
using BrasilBurger.Helpers;

namespace BrasilBurger.Controllers
{
    public class CommandeController : Controller
    {
        private readonly IPanierService _panierService;
        private readonly ApplicationDbContext _context;

        public CommandeController(IPanierService panierService, ApplicationDbContext context)
        {
            _panierService = panierService;
            _context = context;
        }

        public IActionResult Passer() => View();

        [HttpPost]
        public async Task<IActionResult> Passer(TypeCommandeEnum type)
        {
            var client = HttpContext.Session.Get<Client>("client");
            var panier = _panierService.GetPanier();

            var commande = new Commande
            {
                ClientId = client!.Id,
                TypeCommande = type,
                Montant = panier.Total,
                Etat = EtatCommandeEnum.VALIDE
            };

            _context.Commandes.Add(commande);
            await _context.SaveChangesAsync();

            _panierService.Vider();
            return RedirectToAction("Confirmation");
        }

        public IActionResult Confirmation() => View();
    }
    public async Task<IActionResult> MesCommandes()
    {
        var client = HttpContext.Session.Get<Client>("client");
        var commandes = await _commandeService.GetByClient(client!.Id);
        return View(commandes);
    }

}

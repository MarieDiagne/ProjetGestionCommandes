using Microsoft.AspNetCore.Mvc;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.ViewModels.Panier;

namespace BrasilBurger.Controllers
{
    public class PanierController : Controller
    {
        private readonly IPanierService _panierService;

        public PanierController(IPanierService panierService)
        {
            _panierService = panierService;
        }

        public IActionResult Index()
            => View(_panierService.GetPanier());

        public IActionResult Ajouter(int id, string nom, decimal prix)
        {
            _panierService.Ajouter(new LignePanierViewModel
            {
                ProduitId = id,
                Nom = nom,
                Prix = prix,
                Quantite = 1
            });

            return RedirectToAction("Index");
        }

        public IActionResult Supprimer(int id)
        {
            _panierService.Supprimer(id);
            return RedirectToAction("Index");
        }
    }
}

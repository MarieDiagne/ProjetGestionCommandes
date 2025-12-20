using Microsoft.AspNetCore.Mvc;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.ViewModels.Catalogue;
using brasilburgercsharp.Models.Enums;
using System.Linq;

namespace brasilburgercsharp.Controllers
{
    public class CatalogueController : Controller
    {
        private readonly IProduitService _produitService;

        public CatalogueController(IProduitService produitService)
        {
            _produitService = produitService;
        }

        public async Task<IActionResult> Index(string type = "TOUT")
        {
            var produits = await _produitService.GetCatalogueAsync(type);
            var model = new CatalogueViewModel();
            model.Produits = produits;
            model.TypeFiltre = type;
            return View(model);
        }

        public async Task<IActionResult> DetailsBurger(int id)
        {
            var burger = await _produitService.GetBurgerDetailsAsync(id);
            if (burger == null)
            {
                return NotFound();
            }

            var complements = await _produitService.GetAvailableComplementsAsync();
            
            var model = new BurgerViewModel();
            model.Burger = burger;
            model.Frites = complements.Where(c => c.Type == TypeComplementEnum.FRITES).ToList();
            model.Boissons = complements.Where(c => c.Type == TypeComplementEnum.BOISSON).ToList();

            return View(model);
        }
    }
}
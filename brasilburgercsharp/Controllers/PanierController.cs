using Microsoft.AspNetCore.Mvc;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Repositories.Interfaces;
using System.Text.Json;

namespace brasilburgercsharp.Repositories.Implementations
{
    public class PanierController : Controller
    {
        private readonly IProduitRepository _repo;
        private const string SessionKey = "MonPanier";

        public PanierController(IProduitRepository repo) => _repo = repo;

        public IActionResult Index()
        {
            var panier = GetPanier();
            return View(panier);
        }

        public async Task<IActionResult> Ajouter(int id)
        {
            var produit = await _repo.GetByIdAsync(id);
            if (produit != null)
            {
                var panier = GetPanier();
                panier.Add(produit);
                SavePanier(panier);
            }
            return RedirectToAction("Index");
        }

        private List<Produit> GetPanier()
        {
            var data = HttpContext.Session.GetString(SessionKey);
            return data == null ? new List<Produit>() : JsonSerializer.Deserialize<List<Produit>>(data)!;
        }

        private void SavePanier(List<Produit> panier) 
            => HttpContext.Session.SetString(SessionKey, JsonSerializer.Serialize(panier));
    }
}
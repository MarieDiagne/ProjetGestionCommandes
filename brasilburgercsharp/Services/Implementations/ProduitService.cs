using brasilburgercsharp.Repositories.Interfaces;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.Services.Implementations
{
    public class ProduitService : IProduitService
    {
        private readonly IProduitRepository _repo;
        public ProduitService(IProduitRepository repo) => _repo = repo;

        public async Task<IEnumerable<Produit>> GetCatalogueAsync(string type)
        {
            var produits = await _repo.GetAllAsync();
            if (type == "BURGER") return produits.OfType<Burger>().Where(b => !b.Archive);
            if (type == "MENU") return produits.OfType<Menu>().Where(m => !m.Archive);
            return produits.Where(p => !p.Archive);
        }

        public async Task<Burger?> GetBurgerDetailsAsync(int id)
        {
            var p = await _repo.GetByIdAsync(id);
            return p as Burger;
        }

        public async Task<IEnumerable<Complement>> GetAvailableComplementsAsync()
        {
             // Logique pour retourner les compléments non archivés
             return new List<Complement>(); 
        }
    }
}
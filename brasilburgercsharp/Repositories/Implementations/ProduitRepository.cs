using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Repositories.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Repositories.Implementations
{
    public class ProduitRepository : IProduitRepository
    {
        private readonly ApplicationDbContext _context;

        public ProduitRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Produit>> GetAllAsync()
        {
            // Récupération indépendante pour éviter le conflit de type (InvalidCastException)
            var burgers = await _context.Burgers.AsNoTracking().ToListAsync();
            var menus = await _context.Menus.AsNoTracking().ToListAsync();

            var catalogue = new List<Produit>();
            catalogue.AddRange(burgers); // Ajout en tant que Produit
            catalogue.AddRange(menus);   // Ajout en tant que Produit

            return catalogue;
        }

        public async Task<Produit?> GetByIdAsync(int id)
        {
            var burger = await _context.Burgers.AsNoTracking().FirstOrDefaultAsync(b => b.Id == id);
            if (burger != null) return burger;

            return await _context.Menus.AsNoTracking().FirstOrDefaultAsync(m => m.Id == id);
        }

        public async Task AddAsync(Produit produit)
        {
            if (produit is Burger burger) await _context.Burgers.AddAsync(burger);
            else if (produit is Menu menu) await _context.Menus.AddAsync(menu);
            
            await _context.SaveChangesAsync();
        }

        public async Task<IEnumerable<Burger>> GetBurgersAsync() 
            => await _context.Burgers.AsNoTracking().ToListAsync();

        public async Task<IEnumerable<Menu>> GetMenusAsync() 
            => await _context.Menus.Include(m => m.Burgers).AsNoTracking().ToListAsync();
    }
}
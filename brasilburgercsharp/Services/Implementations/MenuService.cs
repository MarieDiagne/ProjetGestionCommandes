using BrasilBurger.Data;
using BrasilBurger.Models.Entities;
using BrasilBurger.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace BrasilBurger.Services.Implementations
{
    public class MenuService : IMenuService
    {
        private readonly ApplicationDbContext _context;

        public MenuService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Menu>> GetAll()
            => await _context.Menus.Where(m => m.IsActive).ToListAsync();

        public async Task<Menu?> GetById(int id)
            => await _context.Menus
                .Include(m => m.Compositions)
                .ThenInclude(c => c.Burger)
                .FirstOrDefaultAsync(m => m.Id == id);
    }
}

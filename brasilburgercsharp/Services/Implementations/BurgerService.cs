using BrasilBurger.Models.Entities;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Data;
using Microsoft.EntityFrameworkCore;

namespace BrasilBurger.Services.Implementations
{
    public class BurgerService : IBurgerService
    {
        private readonly ApplicationDbContext _context;

        public BurgerService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Burger>> GetAll()
            => await _context.Burgers.Where(b => b.IsActive).ToListAsync();

        public async Task<Burger?> GetById(int id)
            => await _context.Burgers.FirstOrDefaultAsync(b => b.Id == id);
    }
}

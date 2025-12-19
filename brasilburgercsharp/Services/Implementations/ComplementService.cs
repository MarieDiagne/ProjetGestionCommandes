using BrasilBurger.Data;
using BrasilBurger.Models.Entities;
using BrasilBurger.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace BrasilBurger.Services.Implementations
{
    public class ComplementService : IComplementService
    {
        private readonly ApplicationDbContext _context;

        public ComplementService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Complement>> GetAll()
            => await _context.Complements.Where(c => c.IsActive).ToListAsync();
    }
}

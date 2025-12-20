using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Repositories.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Repositories.Implementations
{
    public class ComplementRepository : IComplementRepository
    {
        private readonly ApplicationDbContext _context;
        public ComplementRepository(ApplicationDbContext context) => _context = context;

        // Assurez-vous que le retour est bien Task<IEnumerable<Complement>>
        public async Task<IEnumerable<Complement>> GetAllAsync()
        {
            return await _context.Complements.ToListAsync();
        }

        public async Task<Complement?> GetByIdAsync(int id)
        {
            return await _context.Complements.FindAsync(id);
        }
    }
}
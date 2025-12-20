using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Repositories.Interfaces;
using brasilburgercsharp.Data;
using Microsoft.EntityFrameworkCore;
using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Repositories.Implementations
{
    public class CommandeRepository : ICommandeRepository
    {
        private readonly ApplicationDbContext _context;

        public CommandeRepository(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<List<Commande>> GetAllAsync()
        {
            return await _context.Commandes.Include(c => c.Lignes).ToListAsync();
        }

        public async Task<Commande?> GetByIdAsync(int id)
        {
            return await _context.Commandes
                .Include(c => c.Lignes)
                .FirstOrDefaultAsync(c => c.Id == id);
        }

        public async Task<List<Commande>> GetByClientIdAsync(int clientId)
        {
            return await _context.Commandes
                .Where(c => c.ClientId == clientId)
                .Include(c => c.Lignes)
                .ToListAsync();
        }

        public async Task<List<Commande>> GetByClientIdAndEtatAsync(int clientId, EtatCommandeEnum etat)
        {
            return await _context.Commandes
                .Where(c => c.ClientId == clientId && c.Etat == etat)
                .Include(c => c.Lignes)
                .ToListAsync();
        }

        public async Task AddAsync(Commande commande)
        {
            await _context.Commandes.AddAsync(commande);
            await _context.SaveChangesAsync();
        }

        public async Task UpdateAsync(Commande commande)
        {
            _context.Commandes.Update(commande);
            await _context.SaveChangesAsync();
        }

        public async Task DeleteAsync(int id)
        {
            var commande = await _context.Commandes.FindAsync(id);
            if (commande != null)
            {
                _context.Commandes.Remove(commande);
                await _context.SaveChangesAsync();
            }
        }
        
    }
}

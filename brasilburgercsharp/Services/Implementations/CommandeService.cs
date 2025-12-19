using BrasilBurger.Data;
using BrasilBurger.Models.Entities;
using BrasilBurger.Services.Interfaces;
using Microsoft.EntityFrameworkCore;

namespace BrasilBurger.Services.Implementations
{
    public class CommandeService : ICommandeService
    {
        private readonly ApplicationDbContext _context;

        public CommandeService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<IEnumerable<Commande>> GetByClient(int clientId)
            => await _context.Commandes
                .Where(c => c.ClientId == clientId)
                .Include(c => c.Paiement)
                .ToListAsync();
    }
}

using BrasilBurger.Data;
using BrasilBurger.Models.Entities;
using BrasilBurger.Models.Enums;
using BrasilBurger.Services.Interfaces;

namespace BrasilBurger.Services.Implementations
{
    public class PaiementService : IPaiementService
    {
        private readonly ApplicationDbContext _context;

        public PaiementService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<Paiement> Payer(int commandeId, decimal montant, ModePaiementEnum mode)
        {
            var paiement = new Paiement
            {
                CommandeId = commandeId,
                Montant = montant,
                ModePaiement = mode
            };

            _context.Paiements.Add(paiement);
            await _context.SaveChangesAsync();

            return paiement;
        }
    }
}

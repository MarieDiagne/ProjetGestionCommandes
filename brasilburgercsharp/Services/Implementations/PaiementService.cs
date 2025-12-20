using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Models.Enums;
using brasilburgercsharp.Services.Interfaces;

namespace brasilburgercsharp.Services.Implementations
{
    public class PaiementService : IPaiementService
    {
        private readonly ApplicationDbContext _context;
        public PaiementService(ApplicationDbContext context)
        {
            _context = context;
        }

        public async Task<bool> EnregistrerPaiement(int commandeId, decimal montant, ModePaiementEnum mode)
        {
            var paiement = new Paiement
            {
                CommandeId = commandeId,
                Montant = montant,
                ModePaiement = mode,
                DatePaiement = DateTime.UtcNow
            };
            
            _context.Paiements.Add(paiement);
            int result = await _context.SaveChangesAsync();
            return result > 0;
        }
    }
}
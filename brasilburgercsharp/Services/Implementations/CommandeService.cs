using brasilburgercsharp.Data;
using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.ViewModels.Commande;
using Microsoft.EntityFrameworkCore;

namespace brasilburgercsharp.Services.Implementations
{
    public class CommandeService : ICommandeService
    {
        private readonly ApplicationDbContext _context;
        private readonly IPanierService _panierService;

        public CommandeService(ApplicationDbContext context, IPanierService panierService)
        {
            _context = context;
            _panierService = panierService;
        }

        public async Task<int> CreerCommandeAsync(CommandeViewModel model, int clientId)
        {
            var panier = _panierService.GetPanier();
            
            // 1. Création de l'objet Commande
            var nouvelleCommande = new Commande
            {
                ClientId = clientId,
                DateCommande = DateTime.Now,
                MontantTotal = panier.Total,
                ZoneId = model.ZoneId,
                TypeCommande = model.TypeCommande,
                Etat = Models.Enums.EtatCommandeEnum.VALIDEE
            };

            // 2. Transformation des lignes du panier en LigneCommande
            foreach (var item in panier.Lignes)
            {
                nouvelleCommande.Lignes.Add(new LigneCommande
                {
                    ProduitId = item.ProduitId,
                    Quantite = item.Quantite,
                    PrixUnitaire = item.Prix,
                    TypeProduit = item.TypeProduit
                });
            }

            _context.Commandes.Add(nouvelleCommande);
            await _context.SaveChangesAsync();
            
            return nouvelleCommande.Id;
        }

        public async Task<IEnumerable<Commande>> GetCommandesByClientAsync(int clientId)
        {
            return await _context.Commandes
                .Where(c => c.ClientId == clientId)
                .OrderByDescending(c => c.DateCommande)
                .ToListAsync();
        }

        public async Task<Commande?> GetDetailsAsync(int id)
        {
            return await _context.Commandes
                .Include(c => c.Lignes)
                .Include(c => c.Zone)
                .FirstOrDefaultAsync(c => c.Id == id);
        }
    }
}
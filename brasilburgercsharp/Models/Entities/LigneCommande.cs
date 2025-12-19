using BrasilBurger.Models.Enums;

namespace BrasilBurger.Models.Entities
{
    public class LigneCommande
    {
        public int Id { get; set; }

        public int Quantite { get; set; }

        public decimal PrixUnitaire { get; set; }

        public TypeProduitEnum TypeProduit { get; set; }

        public int ProduitId { get; set; }

        // Commande
        public int CommandeId { get; set; }
        public Commande Commande { get; set; } = null!;
    }
}

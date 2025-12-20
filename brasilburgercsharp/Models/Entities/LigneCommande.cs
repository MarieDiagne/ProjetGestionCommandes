using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Models.Entities
{
    public class LigneCommande
    {
        public int Id { get; set; }
        public int CommandeId { get; set; }
        public int ProduitId { get; set; }
        public int Quantite { get; set; }
        public decimal PrixUnitaire { get; set; } // Vérifiez l'orthographe
        public TypeProduitEnum TypeProduit { get; set; } // Vérifiez l'orthographe
        
    }
}
using brasilburgercsharp.ViewModels.Panier;
using System.Linq; // Nécessaire pour Sum()

namespace brasilburgercsharp.Helpers
{
    public static class PriceCalculator
    {
        public static decimal CalculateTotal(PanierViewModel panier)
        {
            if (panier == null || panier.Lignes == null) return 0;
            
            // Correction ici : utiliser .Prix au lieu de .PrixUnitaire
            return panier.Lignes.Sum(l => l.Prix * l.Quantite);
        }
    }
}
using System.Collections.Generic;

namespace BrasilBurger.ViewModels.Panier
{
    public class PanierViewModel
    {
        public List<LignePanierViewModel> Lignes { get; set; } = new();
        public decimal Total => Lignes.Sum(l => l.Prix * l.Quantite);
    }
}

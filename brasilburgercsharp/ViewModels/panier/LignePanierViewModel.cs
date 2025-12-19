namespace BrasilBurger.ViewModels.Panier
{
    public class LignePanierViewModel
    {
        public int ProduitId { get; set; }
        public string Nom { get; set; } = null!;
        public decimal Prix { get; set; }
        public int Quantite { get; set; }
    }
}

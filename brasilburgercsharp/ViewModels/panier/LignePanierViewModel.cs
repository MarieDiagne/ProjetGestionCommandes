namespace brasilburgercsharp.ViewModels.panier
{
    public class LignePanierViewModel
    {
        public int ProduitId { get; set; }
        public string NomProduit { get; set; }
        public int Quantite { get; set; }
        public decimal PrixUnitaire { get; set; }
        public string TypeProduit { get; set; } // BURGER, MENU, COMPLEMENT
    }
}
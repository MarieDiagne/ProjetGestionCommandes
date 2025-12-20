namespace brasilburgercsharp.ViewModels.Panier
{
    public class PanierViewModel
    {
        public List<LignePanierViewModel> Lignes { get; set; } = new List<LignePanierViewModel>();
        public decimal Total => Lignes.Sum(l => l.Prix * l.Quantite);
    }

    public class LignePanierViewModel
    {
        public int ProduitId { get; set; }
        public string NomProduit { get; set; } = string.Empty;
        public decimal Prix { get; set; }
        public int Quantite { get; set; }
        public string? Image { get; set; }
        public brasilburgercsharp.Models.Enums.TypeProduitEnum TypeProduit { get; set; }
    }
}
using brasilburgercsharp.Models.Entities;

namespace brasilburgercsharp.ViewModels.Catalogue
{
    public class CatalogueViewModel
    {
        public IEnumerable<Produit> Produits { get; set; } = new List<Produit>();
        public string TypeFiltre { get; set; } = "TOUT";
    }
}
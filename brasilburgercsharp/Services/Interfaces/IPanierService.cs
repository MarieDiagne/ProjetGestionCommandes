using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.ViewModels.Panier;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface IPanierService
    {
        // Doit retourner l'objet PanierViewModel complet
        PanierViewModel GetPanier();

        // Doit accepter l'objet Produit (qui sera transformé en ligne dans le service)
        void AjouterProduit(Produit produit);

        void ViderPanier();
    }
}
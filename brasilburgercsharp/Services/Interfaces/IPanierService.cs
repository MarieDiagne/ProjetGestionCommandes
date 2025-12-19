using BrasilBurger.ViewModels.Panier;

namespace BrasilBurger.Services.Interfaces
{
    public interface IPanierService
    {
        PanierViewModel GetPanier();
        void Ajouter(LignePanierViewModel ligne);
        void Supprimer(int produitId);
        void Vider();
    }
}

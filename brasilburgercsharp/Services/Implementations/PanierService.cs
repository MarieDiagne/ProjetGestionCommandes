using BrasilBurger.Services.Interfaces;
using BrasilBurger.ViewModels.Panier;
using Microsoft.AspNetCore.Http;
using BrasilBurger.Helpers;

namespace BrasilBurger.Services.Implementations
{
    public class PanierService : IPanierService
    {
        private readonly IHttpContextAccessor _http;

        public PanierService(IHttpContextAccessor http)
        {
            _http = http;
        }

        private ISession Session => _http.HttpContext!.Session;

        public PanierViewModel GetPanier()
            => Session.Get<PanierViewModel>("panier") ?? new PanierViewModel();

        public void Ajouter(LignePanierViewModel ligne)
        {
            var panier = GetPanier();
            var exist = panier.Lignes.FirstOrDefault(l => l.ProduitId == ligne.ProduitId);

            if (exist != null) exist.Quantite++;
            else panier.Lignes.Add(ligne);

            Session.Set("panier", panier);
        }

        public void Supprimer(int produitId)
        {
            var panier = GetPanier();
            panier.Lignes.RemoveAll(l => l.ProduitId == produitId);
            Session.Set("panier", panier);
        }

        public void Vider() => Session.Remove("panier");
    }
}

using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.Models.Enums;
using brasilburgercsharp.Services.Interfaces;
using brasilburgercsharp.ViewModels.Panier;
using Microsoft.AspNetCore.Http;
using System.Text.Json;

namespace brasilburgercsharp.Services.Implementations
{
    public class PanierService : IPanierService
    {
        private readonly IHttpContextAccessor _httpContextAccessor;
        private const string SessionKey = "PanierBrasilBurger";

        public PanierService(IHttpContextAccessor httpContextAccessor)
        {
            _httpContextAccessor = httpContextAccessor;
        }

        // Implémentation de GetPanier (Retourne PanierViewModel)
        public PanierViewModel GetPanier()
        {
            var session = _httpContextAccessor.HttpContext?.Session;
            if (session == null) return new PanierViewModel();

            string? json = session.GetString(SessionKey);
            return json == null ? new PanierViewModel() : JsonSerializer.Deserialize<PanierViewModel>(json)!;
        }

        // Implémentation de AjouterProduit (Prend un Produit en paramètre)
        public void AjouterProduit(Produit produit)
        {
            var panier = GetPanier();
            var ligne = panier.Lignes.FirstOrDefault(l => l.ProduitId == produit.Id);

            if (ligne == null)
            {
                panier.Lignes.Add(new LignePanierViewModel
                {
                    ProduitId = produit.Id,
                    NomProduit = produit.Nom,
                    Prix = produit.Prix,
                    Quantite = 1,
                    Image = produit.Image,
                    // Utilisation de l'énumération correcte
                    TypeProduit = produit is Menu ? TypeProduitEnum.MENU : TypeProduitEnum.BURGER
                });
            }
            else
            {
                ligne.Quantite++;
            }

            SavePanier(panier);
        }

        // Implémentation de ViderPanier
        public void ViderPanier()
        {
            _httpContextAccessor.HttpContext?.Session.Remove(SessionKey);
        }

        private void SavePanier(PanierViewModel panier)
        {
            var json = JsonSerializer.Serialize(panier);
            _httpContextAccessor.HttpContext?.Session.SetString(SessionKey, json);
        }
    }
}
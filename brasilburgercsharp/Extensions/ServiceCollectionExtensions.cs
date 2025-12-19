using Microsoft.Extensions.DependencyInjection;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Services.Implementations;

namespace BrasilBurger.Extensions
{
    public static class ServiceCollectionExtensions
    {
        public static IServiceCollection AddApplicationServices(this IServiceCollection services)
        {
            // 🔐 Authentification
            services.AddScoped<IAuthService, AuthService>();

            // 🍔 Catalogue
            services.AddScoped<IBurgerService, BurgerService>();
            services.AddScoped<IMenuService, MenuService>();
            services.AddScoped<IComplementService, ComplementService>();

            // 🛒 Panier
            services.AddScoped<IPanierService, PanierService>();

            // 📦 Commandes
            services.AddScoped<ICommandeService, CommandeService>();

            // 💳 Paiement
            services.AddScoped<IPaiementService, PaiementService>();

            return services;
        }
    }
}

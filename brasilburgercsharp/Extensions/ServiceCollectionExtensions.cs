using Microsoft.Extensions.DependencyInjection;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Services.Implementations;

namespace BrasilBurger.Extensions
{
    public static class ServiceCollectionExtensions
    {
        public static IServiceCollection AddApplicationServices(this IServiceCollection services)
        {
            services.AddScoped<IAuthService, AuthService>();
            return services;
        }
    }
}

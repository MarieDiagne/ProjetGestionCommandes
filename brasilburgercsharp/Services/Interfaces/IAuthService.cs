using BrasilBurger.Models.Entities;

namespace BrasilBurger.Services.Interfaces
{
    public interface IAuthService
    {
        Task<Client?> Login(string email, string password);
        Task Register(Client client);
    }
}

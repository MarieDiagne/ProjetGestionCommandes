using brasilburgercsharp.Models.Entities;
using brasilburgercsharp.ViewModels.Account;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface IClientService
    {
        Task<Client?> AuthenticateAsync(string email, string password);
        Task<bool> RegisterAsync(RegisterViewModel model);
    }
}
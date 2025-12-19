using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface IClientRepository : IRepository<Client>
    {
        Task<Client?> GetClientByUtilisateurIdAsync(int utilisateurId);
        Task<Client?> GetClientWithCommandesAsync(int id);
        Task<Utilisateur?> GetUtilisateurByEmailAsync(string email);
        Task<bool> EmailExistsAsync(string email);
    }



}
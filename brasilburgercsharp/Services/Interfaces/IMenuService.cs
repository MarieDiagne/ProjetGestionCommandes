using BrasilBurger.Models.Entities;

namespace BrasilBurger.Services.Interfaces
{
    public interface IMenuService
    {
        Task<IEnumerable<Menu>> GetAll();
        Task<Menu?> GetById(int id);
    }
}

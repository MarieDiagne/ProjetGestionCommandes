using BrasilBurger.Models.Entities;

namespace BrasilBurger.Services.Interfaces
{
    public interface IBurgerService
    {
        Task<IEnumerable<Burger>> GetAll();
        Task<Burger?> GetById(int id);
    }
}

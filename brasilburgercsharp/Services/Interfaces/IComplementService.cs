using BrasilBurger.Models.Entities;

namespace BrasilBurger.Services.Interfaces
{
    public interface IComplementService
    {
        Task<IEnumerable<Complement>> GetAll();
    }
}

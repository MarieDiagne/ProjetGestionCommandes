using System.Linq.Expressions;

namespace BrasilBurger.Repositories.Interfaces
{
    public interface IUnitOfWork : IDisposable
    {
        IBurgerRepository Burgers { get; }
        IMenuRepository Menus { get; }
        IComplementRepository Complements { get; }
        IClientRepository Clients { get; }
        ICommandeRepository Commandes { get; }
        IZoneRepository Zones { get; }
        IPaiementRepository Paiements { get; }
        
        Task<int> SaveChangesAsync();
        Task BeginTransactionAsync();
        Task CommitTransactionAsync();
        Task RollbackTransactionAsync();
    }
    


    
}
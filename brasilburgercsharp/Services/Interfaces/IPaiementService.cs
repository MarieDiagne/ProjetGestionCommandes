using BrasilBurger.Models.Entities;
using BrasilBurger.Models.Enums;

namespace BrasilBurger.Services.Interfaces
{
    public interface IPaiementService
    {
        Task<Paiement> Payer(int commandeId, decimal montant, ModePaiementEnum mode);
    }
}

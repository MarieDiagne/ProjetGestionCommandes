using brasilburgercsharp.Models.Enums;

namespace brasilburgercsharp.Services.Interfaces
{
    public interface IPaiementService
    {
        Task<bool> EnregistrerPaiement(int commandeId, decimal montant, ModePaiementEnum mode);
    }
}
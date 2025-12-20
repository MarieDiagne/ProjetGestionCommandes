using Microsoft.AspNetCore.Mvc;
using brasilburgercsharp.Models.Enums;
using brasilburgercsharp.Services.Interfaces;

namespace brasilburgercsharp.Controllers
{
    public class PaiementController : Controller
    {
        private readonly IPaiementService _paiementService;
        public PaiementController(IPaiementService svc) => _paiementService = svc;

        [HttpPost]
        public async Task<IActionResult> Process(int commandeId, decimal montant, ModePaiementEnum mode)
        {
            await _paiementService.EnregistrerPaiement(commandeId, montant, mode);
            return RedirectToAction("Confirmation", "Commande", new { id = commandeId });
        }
    }
}
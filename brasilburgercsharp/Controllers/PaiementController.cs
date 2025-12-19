using Microsoft.AspNetCore.Mvc;
using BrasilBurger.Services.Interfaces;
using BrasilBurger.Models.Enums;

namespace BrasilBurger.Controllers
{
    public class PaiementController : Controller
    {
        private readonly IPaiementService _paiementService;

        public PaiementController(IPaiementService paiementService)
        {
            _paiementService = paiementService;
        }

        [HttpPost]
        public async Task<IActionResult> Payer(int commandeId, decimal montant, ModePaiementEnum mode)
        {
            await _paiementService.Payer(commandeId, montant, mode);
            return RedirectToAction("MesCommandes", "Commande");
        }
    }
}

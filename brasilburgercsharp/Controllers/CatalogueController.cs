using Microsoft.AspNetCore.Mvc;
using BrasilBurger.Services.Interfaces;

namespace BrasilBurger.Controllers
{
    public class CatalogueController : Controller
    {
        private readonly IBurgerService _burgerService;

        public CatalogueController(IBurgerService burgerService)
        {
            _burgerService = burgerService;
        }

        public async Task<IActionResult> Index()
        {
            var burgers = await _burgerService.GetAll();
            return View(burgers);
        }

        public async Task<IActionResult> DetailsBurger(int id)
        {
            var burger = await _burgerService.GetById(id);
            if (burger == null) return NotFound();
            return View(burger);
        }
    }
}

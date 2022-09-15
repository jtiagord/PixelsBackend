package pixels.controller

import org.springframework.web.bind.annotation.*
import pixels.model.*
import pixels.services.CanvasService

@RestController
class CanvasController(val canvasService: CanvasService){

    @GetMapping ("/canvas/{canvasId}")
    fun getCanvas(@PathVariable canvasId : String) : CanvasOutputModel? {
        return canvasService.getCanvas(canvasId)?.toCanvasOutputModel()
    }

    @PostMapping ("/canvas")
    fun createCanvas(@RequestBody canvas : CanvasInputModel) : CanvasOutputModel?{
       return canvasService.createCanvas(canvas.toCanvas())?.toCanvasOutputModel()
    }

    @PostMapping ("/canvas/{canvasId}/line")
    fun createCanvas(@PathVariable canvasId : String , @RequestBody line : Shape) : Boolean{
        return canvasService.addLine(canvasId,line)
    }
}
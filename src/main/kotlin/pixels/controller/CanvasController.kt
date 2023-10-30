package pixels.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import pixels.errorHandlers.ResourceNotFoundException
import pixels.model.*
import pixels.services.CanvasService
import javax.servlet.http.HttpServletResponse

@RestController
class CanvasController(val canvasService: CanvasService){

    @GetMapping ("/canvas/{canvasId}")
    fun getCanvas(@PathVariable canvasId : String) : CanvasOutputModel? {

        return canvasService.getCanvas(canvasId)?.toCanvasOutputModel()?:
            throw ResourceNotFoundException("Unable to find canvas")
    }

    @PostMapping ("/canvas")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createCanvas(@RequestBody canvas : CanvasInputModel, resp : HttpServletResponse) : CanvasOutputModel?{

       val canvasOut =
           canvasService.createCanvas(canvas.toCanvas())?.toCanvasOutputModel() ?:
           throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                                 "Unable to create canvas try again later")

       resp.setHeader("Location", "/canvas/${canvasOut.id}")
       return canvasOut
    }

    @PostMapping ("/canvas/{canvasId}/shape")
    fun createShape(@PathVariable canvasId : String , @RequestBody line : Shape) : Boolean{
        return canvasService.addLine(canvasId,line)
    }

    @PostMapping ("/canvas/{canvasId}/line")
    fun createLine(@PathVariable canvasId : String , @RequestBody line : Line) : Boolean{
        return canvasService.addLine(canvasId,line)
    }
    @PostMapping ("/canvas/{canvasId}/freedraw")
    fun createFreeDraw(@PathVariable canvasId : String , @RequestBody line : FreeDraw) : Boolean{
        return canvasService.addLine(canvasId,line)
    }
    @PostMapping ("/canvas/{canvasId}/circle")
    fun createCanvas(@PathVariable canvasId : String , @RequestBody line : Circle) : Boolean{
        return canvasService.addLine(canvasId,line)
    }
    @PostMapping ("/canvas/{canvasId}/rectangle")
    fun createCanvas(@PathVariable canvasId : String , @RequestBody line : Rectangle) : Boolean{
        return canvasService.addLine(canvasId,line)
    }
}
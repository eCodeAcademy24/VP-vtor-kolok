package mk.ecode.artists.web.controller;

import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {

    private final SongService songService;
    private final ArtistService artistService;

    public SongController(SongService songService, ArtistService artistService) {
        this.songService = songService;
        this.artistService = artistService;
    }

    @GetMapping("/{songId}")
    public String getSongDetails(@PathVariable Long songId, Model model) {
        model.addAttribute("song", songService.findById(songId));
        model.addAttribute("artists", artistService.listArtists());

        return "songDetails";
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("songs", songService.listSongs());
        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("artists", artistService.listArtists());

        return "add-form";
    }

    @PostMapping
    public String saveSong(@RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam List<Long> artistsId
    ) {
        songService.create(trackId, title, genre, releaseYear, artistsId);

        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        model.addAttribute("artists", artistService.listArtists());

        return "add-form";
    }

    @PostMapping("/edit/{id}")
    public String editSong(@PathVariable Long id,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam List<Long> artistsId
    ) {
        songService.update(id, trackId, title, genre, releaseYear, artistsId);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs";
    }
}

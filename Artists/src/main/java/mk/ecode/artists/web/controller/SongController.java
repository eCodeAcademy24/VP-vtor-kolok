package mk.ecode.artists.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.service.AlbumService;
import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    @GetMapping("/{songId}")
    public String getSongDetails(@PathVariable Long songId, Model model) {
        model.addAttribute("song", songService.findById(songId));
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("albums", albumService.findAll());

        return "songDetails";
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) Long albumId,
                               Model model
    ) {
        model.addAttribute("albums", albumService.findAll());

        if (albumId != null) {
            model.addAttribute("songs", songService.findAllByAlbum_Id(albumId));
        } else {
            model.addAttribute("songs", songService.listSongs());
        }

        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("albums", albumService.findAll());

        return "add-form";
    }

    @PostMapping
    public String saveSong(@RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId
    ) {
        songService.create(trackId, title, genre, releaseYear, albumId);

        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("albums", albumService.findAll());

        return "add-form";
    }

    @PostMapping("/edit/{id}")
    public String editSong(@PathVariable Long id,
                           @RequestParam String trackId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam int releaseYear,
                           @RequestParam Long albumId
    ) {
        songService.update(id, trackId, title, genre, releaseYear, albumId);
        return "redirect:/songs";
    }

    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.delete(id);
        return "redirect:/songs";
    }
}

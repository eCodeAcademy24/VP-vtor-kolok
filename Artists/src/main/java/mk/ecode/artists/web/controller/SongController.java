package mk.ecode.artists.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ecode.artists.model.Song;
import mk.ecode.artists.service.AlbumService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/songs")
@RequiredArgsConstructor
public class SongController {

    private final SongService songService;
    private final AlbumService albumService;

    @GetMapping("/{id}")
    public String getSongDetailsPage(@PathVariable Long id, Model model) {
        model.addAttribute("song", songService.findById(id));
        return "songDetails";
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error,
                               @RequestParam(required = false) Long albumId,
                               Model model) {
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("error", error);

        if (albumId != null) {
            model.addAttribute("songs", songService.findAllByAlbumId(albumId));
        } else {
            model.addAttribute("songs", songService.listSongs());
        }


        return "listSongs";
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        model.addAttribute("albums", albumService.findAll());
        return "add-song";
    }

    @PostMapping("/add")
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
        try {
            Song song = songService.findById(id);
            model.addAttribute("song", song);
            model.addAttribute("albums", albumService.findAll());
            return "add-song";
        } catch (RuntimeException ex) {
            return "redirect:/songs?error=true";
        }
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

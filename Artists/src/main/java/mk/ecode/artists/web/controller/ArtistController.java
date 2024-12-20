package mk.ecode.artists.web.controller;

import mk.ecode.artists.service.ArtistService;
import mk.ecode.artists.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }

    @GetMapping
    public String getArtistsPage(@RequestParam Long songId, Model model) {
        model.addAttribute("songId", songId);
        model.addAttribute("artists", artistService.listArtists());
        return "artistsList";
    }

    @PostMapping
    public String addArtistToSong(@RequestParam Long songId, @RequestParam Long artistId) {
//        songService.addArtistToSong(artistId, songId);
        return "redirect:/songs/" + songId;
    }
}

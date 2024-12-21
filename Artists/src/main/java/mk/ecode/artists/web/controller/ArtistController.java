package mk.ecode.artists.web.controller;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final SongService songService;

    @GetMapping
    public String getArtistsPage(@RequestParam Long songId, Model model) {
        model.addAttribute("songId", songId);
        model.addAttribute("artists", artistService.listArtists());

        return "artistsList";
    }

    @PostMapping
    public String addArtistToSong(@RequestParam Long artistId,
                                  @RequestParam Long songId
    ) {
        songService.addArtistToSong(artistId, songId);
        return "redirect:/songs/" + songId;
    }
}

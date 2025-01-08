package mk.ecode.discussions.service.impl;

import lombok.RequiredArgsConstructor;
import mk.ecode.discussions.model.Discussion;
import mk.ecode.discussions.model.DiscussionTag;
import mk.ecode.discussions.model.User;
import mk.ecode.discussions.model.exceptions.InvalidDiscussionIdException;
import mk.ecode.discussions.repository.DiscussionRepository;
import mk.ecode.discussions.service.DiscussionService;
import mk.ecode.discussions.service.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscussionServiceImpl implements DiscussionService {

    private final DiscussionRepository discussionRepository;
    private final UserService userService;

    @Override
    public List<Discussion> listAll() {
        return discussionRepository.findAll();
    }

    @Override
    public Discussion findById(Long id) {
        return discussionRepository.findById(id).orElseThrow(() -> new InvalidDiscussionIdException(id));
    }

    @Override
    public Discussion create(String title, String description, DiscussionTag discussionTag, List<Long> participants, LocalDate dueDate) {
        Discussion discussion = new Discussion();

        List<User> users = participants.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        discussion.setTitle(title);
        discussion.setDescription(description);
        discussion.setTag(discussionTag);
        discussion.setDueDate(dueDate);
        discussion.setParticipants(users);

        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion update(Long id, String title, String description, DiscussionTag discussionTag, List<Long> participants) {
        Discussion discussion = findById(id);

        List<User> users = participants.stream()
                .map(userService::findById)
                .collect(Collectors.toList());

        discussion.setTitle(title);
        discussion.setDescription(description);
        discussion.setTag(discussionTag);
        discussion.setParticipants(users);

        return discussionRepository.save(discussion);
    }

    @Override
    public Discussion delete(Long id) {
        Discussion discussion = findById(id);

        discussionRepository.delete(discussion);
        return discussion;
    }

    @Override
    public Discussion markPopular(Long id) {
        Discussion discussion = findById(id);

        discussion.setPopular(true);

        return discussionRepository.save(discussion);
    }

    @Override
    public List<Discussion> filter(Long participantId, Integer daysUntilClosing) {
        if (participantId != null && daysUntilClosing != null) {
            return discussionRepository.findAllByParticipantsContainingAndDueDateBefore(
                    userService.findById(participantId),
                    LocalDate.now().plusDays(daysUntilClosing)
            );
        } else if (participantId != null) {
            return discussionRepository.findAllByParticipantsContaining(userService.findById(participantId));
        } else if (daysUntilClosing != null) {
            return discussionRepository.findAllByDueDateBefore(LocalDate.now().plusDays(daysUntilClosing));
        }

        return listAll();
    }
}

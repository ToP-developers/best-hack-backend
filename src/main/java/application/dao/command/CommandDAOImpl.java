package application.dao.command;

import application.entities.CommandEntity;
import application.requests.Command;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommandDAOImpl implements CommandDAO {
    private EntityManager entityManager;

    public CommandDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void create(@NotNull Command[] commands, @NotNull Long id) {
        for (Command command : commands) {
            entityManager.persist(new CommandEntity(command, id));
        }
    }

    @Override
    public List<CommandEntity> get(Long id) {
        final TypedQuery<CommandEntity> query = entityManager.createQuery(
                "SELECT c FROM CommandEntity c WHERE user_id = :user_id",
                CommandEntity.class
        );
        query.setParameter("user_id", id);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}

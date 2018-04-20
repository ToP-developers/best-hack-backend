package application.dao;

import application.entities.UserEntity;
import application.requests.Signup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

@Repository
public class UserDAOImpl implements UserDAO {
    private EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @NotNull
    @Override
    public UserEntity addUser(@NotNull Signup signup) {
        UserEntity entity = new UserEntity(signup);
        entityManager.persist(entity);
        return entity;
    }

    @Nullable
    @Override
    public UserEntity getUserById(@NotNull Long userId) {
        return entityManager.find(UserEntity.class, userId);
    }

    @Nullable
    @Override
    public UserEntity getUserByLogin(@NotNull String login) {
        final TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE login = :login",
                UserEntity.class
        );
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Nullable
    @Override
    public UserEntity getUserByEmail(@NotNull String email) {
        final TypedQuery<UserEntity> query = entityManager.createQuery(
                "SELECT u FROM UserEntity u WHERE email = :email",
                UserEntity.class
        );
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

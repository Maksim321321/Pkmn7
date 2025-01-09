package ru.mirea.Pimkin.pkmn.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mirea.Pimkin.pkmn.dao.CardDao;
import ru.mirea.Pimkin.pkmn.dao.StudentDao;
import ru.mirea.Pimkin.pkmn.entity.CardEntity;
import ru.mirea.Pimkin.pkmn.entity.StudentEntity;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardDao cardDao;
    private final StudentDao studentDao;

    @Override
    public CardEntity getCardById(UUID id) {
        return cardDao.getCardById(id);
    }

    @Override
    public CardEntity getCardByFIO(StudentEntity student) {
        return cardDao.getCardByStudent(student);
    }

    @Override
    public CardEntity getCardByName(String name) {
        return cardDao.getCardByName(name);
    }

    @Override
    @Transactional
    public CardEntity saveCard(CardEntity card) {
        if (cardDao.cardExists(card)) {
            throw new IllegalArgumentException("Карта с таким именем есть");
        }
        if(card.getPokemonOwner() != null){
            if(studentDao.studentExists(card.getPokemonOwner())){
                card.setPokemonOwner(studentDao.getStudentsByFIO(card.getPokemonOwner()).getFirst());
            }
            else {
                card.setPokemonOwner(studentDao.saveStudent(card.getPokemonOwner()));
            }
        }
        if(card.getEvolvesFrom() != null)
        {
            if(cardDao.cardExists(card.getEvolvesFrom())){
                card.setEvolvesFrom(cardDao.getCardByName(card.getEvolvesFrom().getName()));
            }
            else {
                card.setEvolvesFrom(cardDao.saveCard(card.getEvolvesFrom()));
            }
        }

        return cardDao.saveCard(card);
    }


    @Override
    public List<CardEntity> findAllard(){
        return cardDao.findAllCard();
    }


}

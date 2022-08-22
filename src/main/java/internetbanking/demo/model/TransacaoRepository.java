package internetbanking.demo.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;


@Repository
public interface TransacaoRepository extends PagingAndSortingRepository<Transacoes, Long> {

    @Query(nativeQuery = true, value="SELECT id_transacao, tipo_transacao, id_cliente, data_hora, valor " +
            "FROM Transacao  WHERE id_cliente=:id_cliente " +
            "AND data_hora >= :data_inicio " +
            "and data_hora <= :data_fim")
    List<Transacoes> findBydata_inicioBetween(@Param("data_inicio") Date date,
                                              @Param("data_fim") Date date2,
                                              @Param("id_cliente") long id);
}

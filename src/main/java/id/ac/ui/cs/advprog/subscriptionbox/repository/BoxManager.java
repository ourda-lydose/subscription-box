package id.ac.ui.cs.advprog.subscriptionbox.repository;
import id.ac.ui.cs.advprog.subscriptionbox.model.SubscriptionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author muhammad.khadafi
 */
@Repository
public interface BoxManager extends JpaRepository<SubscriptionBox, String> {
}
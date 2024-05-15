package ru.buzynnikov.bidservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.buzynnikov.bidservice.dto.BidDTO;
import ru.buzynnikov.bidservice.exceptions.BidNotFoundException;
import ru.buzynnikov.bidservice.models.Bid;
import ru.buzynnikov.bidservice.models.Consumption;
import ru.buzynnikov.bidservice.models.Product;
import ru.buzynnikov.bidservice.models.Quantity;
import ru.buzynnikov.bidservice.repositories.BidRepository;
import ru.buzynnikov.bidservice.repositories.ProductRepository;
import ru.buzynnikov.bidservice.repositories.QuantityRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BidService {
    private final String FACTOR = "1.2";
    private final BidRepository bidRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    private final QuantityRepository quantityRepository;


    public HashMap<Product, Double> sendBid(List<Consumption> consumptions){
        Long bidId = bidRepository.save(createBid()).getId();
        HashMap<Product, Double> bid = new HashMap<>();
        List<Quantity> quantities = createListQuantity(consumptions,bidId);
        for (Quantity quantity: quantities){
            bid.put(productService.findById(quantity.getProductId()),quantity.getQuantity());
        }
        return bid;
    }

    public Bid getBidById(Long id){
        Optional<Bid> bid = bidRepository.findById(id);
        return bid.orElseThrow(()-> new BidNotFoundException("Заявка не найдена"));
    }

    private Bid createBid(){
        Bid bid = new Bid();
        bid.setTimeCreate(LocalDateTime.now());
//        bid.setUser(bidDTO.getUser());
        return bid;
    }

    private Double setQuantity(BigDecimal weight, Double consumption){
        double quantity = consumption * Double.parseDouble(FACTOR);
        if(weight.doubleValue() != 1) return Math.ceil(quantity/weight.doubleValue());
        return quantity;
    }
    private List<Quantity> createListQuantity(List<Consumption> consumptions,Long bidId){
        List<Quantity> quantities = new ArrayList<>();
        for (Consumption product: consumptions){
            Optional<Product> optional = productRepository.findById(product.getIdProduct());
            if(optional.isPresent()){
                Product newProduct = optional.get();
                quantities.add(createQuantity(newProduct.getId(),
                        setQuantity(newProduct.getWeight(),product.getConsumption()),
                        bidId));
            }
        }
        return quantities;
    }
    private Quantity createQuantity(Long productId, Double quantity, Long bidId){
        Quantity quant = new Quantity();
        quant.setQuantity(quantity);
        quant.setProductId(productId);
        quant.setBidId(bidId);
        return quantityRepository.save(quant);
    }
}

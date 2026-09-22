package se.vermiculus.benchmark.serialization.avro;
import se.vermiculus.benchmark.serialization.model.avro.*;

import java.util.Collections;
import java.util.stream.Collectors;

public class AvroMapper {

    public Order map(se.vermiculus.benchmark.messages.Order order) {
        if (order == null) {
            return null;
        }
        Order avroOrder = new Order();
        avroOrder.setId(order.id());
        avroOrder.setCustomer(map(order.customer()));
        if (order.lines() != null) {
            avroOrder.setLines(order.lines().stream().map(this::map).collect(Collectors.toList()));
        } else {
            avroOrder.setLines(Collections.emptyList());
        }
        avroOrder.setStatus(order.status() != null ? OrderStatus.valueOf(order.status().name()) : null);
        avroOrder.setCreated(order.created());
        avroOrder.setShippingAddress(map(order.shippingAddress()));
        avroOrder.setNotes(order.notes());
        return avroOrder;
    }

    public Customer map(se.vermiculus.benchmark.messages.Customer customer) {
        if (customer == null) {
            return null;
        }
        Customer avroCustomer = new Customer();
        avroCustomer.setId(customer.id());
        avroCustomer.setFirstName(customer.firstName());
        avroCustomer.setLastName(customer.lastName());
        avroCustomer.setEmail(customer.email());
        avroCustomer.setDateOfBirth(customer.dateOfBirth());
        avroCustomer.setVip(customer.vip());
        if (customer.addresses() != null) {
            avroCustomer.setAddresses(customer.addresses().stream().map(this::map).collect(Collectors.toList()));
        } else {
            avroCustomer.setAddresses(Collections.emptyList());
        }
        return avroCustomer;
    }

    public Address map(se.vermiculus.benchmark.messages.Address address) {
        if (address == null) {
            return null;
        }
        Address avroAddress = new Address();
        avroAddress.setStreet(address.street());
        avroAddress.setCity(address.city());
        avroAddress.setZip(address.zip());
        avroAddress.setCountry(address.country());
        return avroAddress;
    }

    public OrderLine map(se.vermiculus.benchmark.messages.OrderLine line) {
        if (line == null) {
            return null;
        }
        OrderLine avroLine = new OrderLine();
        avroLine.setProduct(map(line.product()));
        avroLine.setQuantity(line.quantity() != null ? line.quantity().intValueExact() : 0);
        avroLine.setUnitPrice(line.unitPrice());
        avroLine.setDiscount(line.discount());
        return avroLine;
    }

    public Product map(se.vermiculus.benchmark.messages.Product product) {
        if (product == null) {
            return null;
        }
        Product avroProduct = new Product();
        avroProduct.setSku(product.sku());
        avroProduct.setName(product.name());
        avroProduct.setDescription(product.description());
        avroProduct.setCategory(map(product.category()));
        avroProduct.setPrice(product.price());
        avroProduct.setWeightGrams(product.weightGrams());
        avroProduct.setTags(product.tags() != null ? product.tags() : Collections.emptyList());
        return avroProduct;
    }

    public Category map(se.vermiculus.benchmark.messages.Category category) {
        if (category == null) {
            return null;
        }
        Category avroCategory = new Category();
        avroCategory.setId(category.id());
        avroCategory.setName(category.name());
        avroCategory.setParent(map(category.parent()));
        return avroCategory;
    }
}